//
//
//  Program.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class Program {
    var title = ""
    var description = ""
    var image = UIImage(named: "1")
    var url = ""
    var courses = [Course]()
    
    init(title: String, description: String, image: UIImage, url: String, courses: [Course])
    {
        self.title = title
        self.description = description
        self.image = image
        self.url = url
        self.courses = courses
    }
    
    static func TotalIOSBlueprint() -> Program
    {
        let course1 = Course(title: "Grocery", description: "$500.00", image: UIImage(named: "visa_card")!, programURL: "https://developer.visa.com/")
        
        
        
        let courses = [course1]
        let totaliOSBlueprint = Program(title: "Total iOS Blueprint", description: "Everything you need to develop iOS Apps. No experience required. Build more than 70 projects in your portfolio.", image: UIImage(named: "tib")!, url: "https://developer.visa.com/", courses: courses)
        
        return totaliOSBlueprint
    }
    
    static func SocializeYourApps() -> Program
    {
        let course12 = Course(title: "Clothes", description: "$250.00", image: UIImage(named: "visa_card")!, programURL: "http://learn.developersacademy.io/courses/socialize-your-apps-online")
        
        
        let courses = [course12]
        let socializeYourApps = Program(title: "Socialize Your Apps", description: "iOS Design, Prototype, Animation and Social Network Apps Development wt Parse. Duc's Live Coaching Weekly.", image: UIImage(named: "sya")!, url: "http://developersacademy.squarespace.com/black-friday-sya", courses: courses)
        
        return socializeYourApps
    }
}















