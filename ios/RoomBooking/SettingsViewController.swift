//
//  SettingsViewController.swift
//  MicroMoney
//
//  Created by Andrey Danilkovich on 10/20/18.
//  Copyright © 2018 Andrey Danilkovich. All rights reserved.
//

import UIKit
import CoreMotion // Make sure CoreMotion is imported

class SettingsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    let shortTerm = ["Cancun Vacation","New Car"]
    let longTerm = ["New House","College for Brian"]
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return shortTerm.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: UITableViewCellStyle.default, reuseIdentifier: "cell")
        cell.textLabel?.text = shortTerm[indexPath.row]
        
        return (cell)
    }
    
    

    
    lazy var altimeter = CMAltimeter() // Lazily load CMAltimeter
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view, typically from a nib.
        
        
        
    }
    
//    func startAltimeter() {
//
//        print("Started relative altitude updates.")
//
//        // Check if altimeter feature is available
//        if (CMAltimeter.isRelativeAltitudeAvailable()) {
//
//            self.activityIndicator.startAnimating()
//
//            // Start altimeter updates, add it to the main queue
//            self.altimeter.startRelativeAltitudeUpdates(to: OperationQueue.main, withHandler: { (altitudeData:CMAltitudeData?, error:Error?) in
//
//                if (error != nil) {
//
//                    // If there's an error, stop updating and alert the user
//
//                    self.altimeterSwitch.isOn = false
//                    self.stopAltimeter()
//
//                    let alertView = UIAlertView(title: "Error", message: error!.localizedDescription, delegate: nil, cancelButtonTitle: "OK")
//                    alertView.show()
//
//                } else {
//
//                    let altitude = altitudeData!.relativeAltitude.floatValue    // Relative altitude in meters
//                    let pressure = altitudeData!.pressure.floatValue            // Pressure in kilopascals
//
//                    // Update labels, truncate float to two decimal points
//                    self.altLabel.text = String(format: "%.02f", altitude)
//                    self.pressureLabel.text = String(format: "%.02f", pressure)
//
//                }
//
//            })
//
//        } else {
//
//            let alertView = UIAlertView(title: "Error", message: "Barometer not available on this device.", delegate: nil, cancelButtonTitle: "OK")
//            alertView.show()
//
//        }
//
//    }
//
//    func stopAltimeter() {
//
//        // Reset labels
//        self.altLabel.text = "-"
//        self.pressureLabel.text = "-"
//
//        self.altimeter.stopRelativeAltitudeUpdates() // Stop updates
//
//        self.activityIndicator.stopAnimating() // Hide indicator
//
//        print("Stopped relative altitude updates.")
//
//    }
//
//    @IBAction func switchDidChange(_ senderSwitch: UISwitch) {
//
//        if (senderSwitch.isOn == true) {
//            self.startAltimeter()
//        } else {
//            self.stopAltimeter()
//        }
//
//    }
    
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}
