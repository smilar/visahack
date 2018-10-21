//
//  Course.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit

class Course
{
    var title = ""
    var description = ""
    var image: UIImage!
    var programURL = ""
    var current = 0.0
    var max = 0.0
    var progress = 0.0
    
    init(title: String, description: String, image: UIImage, programURL: String, progress: Double, current: Double, max: Double)
    {
        self.title = title
        self.description = description
        self.image = image
        self.programURL = programURL
        self.current = current
        self.max = max
        self.progress = progress
    }
}
