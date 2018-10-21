//
//  Goals.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import Foundation

import UIKit

class Goal
{
    var goalTitle = ""
    var goalCurrent = ""
    var goalTarget = ""
    var image: UIImage!
    var programURL = ""
    
    init(goalTitle: String)
    {
        self.goalTitle = goalTitle
    }
}

class Goals {
    var title = ""
    var goalCurrent = ""
    var goalTarget = ""
    var description = ""
    var image = UIImage(named: "1")
    var url = ""
    var goals = [Goal]()
    
    init(title: String, goalCurrent: String, goalTarget: String, url: String, goals: [Goal])
    {
        self.title = title
        self.goalCurrent = goalCurrent
        self.goalTarget = goalTarget
        self.goals = goals
    }
    
    static func ShortTerm() -> Goal
    {

        let goal1 = Goal(goalTitle: "House")
        let goal2 = Goal(goalTitle: "House")
        let goal3 = Goal(goalTitle: "House")
        let goals = [goal1, goal2, goal3]
        
        let shortTerm = Goal(goalTitle: "house")
        
        return shortTerm
    }
    
    static func LongTerm() -> Goal
    {
        let goal1 = Goal(goalTitle: "House")
        let goal2 = Goal(goalTitle: "House")
        let goal3 = Goal(goalTitle: "House")
        let goals = [goal1, goal2, goal3]
        
        let longTerm = Goal(goalTitle: "house")
        
        return longTerm
    }
}

