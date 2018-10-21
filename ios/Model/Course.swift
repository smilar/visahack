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
    
    init(title: String, description: String, image: UIImage, programURL: String)
    {
        self.title = title
        self.description = description
        self.image = image
        self.programURL = programURL
    }
}
