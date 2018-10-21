//
//  CardViewController.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class CardViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        let tap = UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:)))
        tap.cancelsTouchesInView = false
        self.view.addGestureRecognizer(tap)
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    @IBAction func addAction(_ sender: Any) {
        
        let virtualCard = Course(title: "New Card", description: "$500.00", image: UIImage(named: "visa_card")!, programURL: "https://developer.visa.com/", progress: 0.8, current: 415.97, max: 500.00)
        
        let cards = [virtualCard]
        let newCard = Program(title: "Section New Card", description: "This card has a category", image: UIImage(named: "tib")!, url: "https://developer.visa.com/", courses: cards)
        
        var programs: [Program] = [Program.TotalIOSBlueprint(), Program.SocializeYourApps()]
        
        programs.insert(newCard, at: 0)
        
//        tblView.beginUpdates()
//        tblView.insertRows(at: [IndexPath.init(row: 0, section: 0)], with: .automatic)
//        tblView.endUpdates()
        
        navigationController?.popViewController(animated: true)
        
        dismiss(animated: true, completion: nil)
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        
        textField.resignFirstResponder()
        
        return true
    }
    
    func doneButtonAction() {
        self.view.endEditing(true)
    }
    
}
