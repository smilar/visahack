//
//  ProgramsTableViewController.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit
import SafariServices

class ProgramsTableViewController: UITableViewController {

//    var programs: [Program] = [Program.TotalIOSBlueprint(), Program.SocializeYourApps()]
    var programs: [Program] = [Program.TotalIOSBlueprint()]
    
    // MARK: - View Controller Lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Make the row height dynamic
        tableView.estimatedRowHeight = tableView.rowHeight
        tableView.rowHeight = UITableViewAutomaticDimension
    }
    
    // MARK: - UITableViewDataSource

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return programs.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
    {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Course Cell", for: indexPath) as! CourseTableViewCell
        
//        let program = programs[indexPath.row]
//        let mockCourse = Course(title: program.title, description: program.description, image: program.image!, programURL: program.url)
//        cell.course = mockCourse
        
        return cell
    }
    
    // MARK: - UITableViewDelegate
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath)
    {
        let program = programs[indexPath.row]
        let url = program.url
        
        showWebsite(url)
    }
    
    // MARK: - Show Webpage with SFSafariViewController
    
    func showWebsite(_ url: String)
    {
        let webVC = SFSafariViewController(url: URL(string: url)!)
        webVC.delegate = self
        
        self.present(webVC, animated: true, completion: nil)
    }
}

extension ProgramsTableViewController : SFSafariViewControllerDelegate
{
    func safariViewControllerDidFinish(_ controller: SFSafariViewController)
    {
        controller.dismiss(animated: true, completion: nil)
    }
}
