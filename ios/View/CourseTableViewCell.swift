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
    @IBOutlet weak var current: UILabel!
    @IBOutlet weak var max: UILabel!
    @IBOutlet var progress: UIProgressView!
    @IBOutlet var limit: UILabel!
    
    var course: Course! {
        didSet {
            updateUI()
        }
    }
    
    func updateUI()
    {
        self.courseImageView.image = course.image
        self.title.text = course.title
        self.limit.text = String(format:"$%.1f", course.current)
        self.max.text = String(format:"$%.1f", course.max)
        self.current.text = String(format:"$%.1f", course.current)
        self.progress.setProgress(Float(course.current/course.max), animated: true)
        
        self.courseImageView?.layer.cornerRadius = 5.0
        self.courseImageView?.layer.masksToBounds = true
        
    }
}
