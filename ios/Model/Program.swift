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
        let course1 = Course(title: "Grocery", description: "$500.00", image: UIImage(named: "visa_card")!, programURL: "https://developer.visa.com/", progress: 0.8, current: 415.97, max: 500.00)
        
        
        
        let courses = [course1]
        let totaliOSBlueprint = Program(title: "Total iOS Blueprint", description: "Everything you need to develop iOS Apps. No experience required. Build more than 70 projects in your portfolio.", image: UIImage(named: "tib")!, url: "https://developer.visa.com/", courses: courses)
        
        return totaliOSBlueprint
    }
    
    static func SocializeYourApps() -> Program
    {
        let course12 = Course(title: "Coffee", description: "$250.00", image: UIImage(named: "visa_card_blue")!, programURL: "http://learn.developersacademy.io/courses/socialize-your-apps-online", progress: 0.5, current: 235.45, max: 300.00)
        
        let course1 = Course(title: "Clothes", description: "$250.00", image: UIImage(named: "visa_card_very_blue")!, programURL: "http://learn.developersacademy.io/courses/socialize-your-apps-online", progress: 0.5, current: 56.42, max: 200.00)
        
        let course2 = Course(title: "Entertainment", description: "$500.00", image: UIImage(named: "visa_card_yellow")!, programURL: "http://learn.developersacademy.io/courses/socialize-your-apps-online", progress: 0.5, current: 124.51, max: 500.00)
        
        let course3 = Course(title: "Fuel", description: "$250.00", image: UIImage(named: "visa_card_pink")!, programURL: "http://learn.developersacademy.io/courses/socialize-your-apps-online", progress: 0.5, current: 100.24, max: 150.00)
        
        
        
        let courses = [course12, course1, course2, course3]
        let socializeYourApps = Program(title: "Socialize Your Apps", description: "iOS Design, Prototype, Animation and Social Network Apps Development wt Parse. Duc's Live Coaching Weekly.", image: UIImage(named: "sya")!, url: "http://developersacademy.squarespace.com/black-friday-sya", courses: courses)
        
        return socializeYourApps
    }
}















