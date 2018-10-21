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
        self.goalCurrent.text = String(format:"$%.2f", goal.goalCurrent)
        self.goalMax.text = String(format:"$%.2f", goal.goalTarget)
        self.goalProgress.setProgress(Float(goal.goalProgress), animated: true)
        
        
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
