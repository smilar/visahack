//
//  GoalTableViewCell.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class GoalTableViewCell: UITableViewCell {

    @IBOutlet var goalName: UILabel!
    @IBOutlet var goalCurrent: UILabel!
    @IBOutlet var goalMax: UILabel!
    @IBOutlet var goalProgress: UIView!
    
    
    var goal: Goals! {
        didSet {
            updateUI()
        }
    }
    
    func updateUI()
    {
//        self.courseImageView.image = course.image
//        self.title.text = course.title
//        self.courseDescription.text = course.description
//        
//        self.courseImageView?.layer.cornerRadius = 5.0
//        self.courseImageView?.layer.masksToBounds = true
        
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
