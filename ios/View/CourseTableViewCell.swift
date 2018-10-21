//
//  TableViewCell.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class CourseTableViewCell: UITableViewCell {

    @IBOutlet weak var courseImageView: UIImageView!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var courseDescription: UILabel!
    
    var course: Course! {
        didSet {
            updateUI()
        }
    }
    
    func updateUI()
    {
        self.courseImageView.image = course.image
        self.title.text = course.title
        self.courseDescription.text = course.description
        
        self.courseImageView?.layer.cornerRadius = 5.0
        self.courseImageView?.layer.masksToBounds = true
        
    }
}
