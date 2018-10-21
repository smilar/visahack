//
//  GoalTableViewCell.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class GoalTableViewCell: UITableViewCell {

    @IBOutlet weak var goalName: UILabel!
    @IBOutlet weak var goalCurrent: UILabel!
    @IBOutlet weak var goalMax: UILabel!
    @IBOutlet weak var goalProgress: UIProgressView!
    
    
    var goal: Goal! {
        didSet {
            updateUI()
        }
    }
    
    func updateUI()
    {
    
        self.goalName.text =  goal.goalTitle
        self.goalCurrent.text = "$100.00"
        self.goalMax.text = "$500.00"
        self.goalProgress.setProgress(0.5, animated: true)
        
        
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
