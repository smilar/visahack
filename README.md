# Money Clips

One Paragraph of project description goes here

## Getting Started

Smart budgeting app proactively helps household families to manage their finances and savings by:
* The app helps the user to create short life goals like Emergency fund or a vacation. Or long life goals like 
* It also allows users to create envelopes for certain spending categories (groceries , coffee, dining, etc) and set spending limit for each category.

### Prerequisites

* VISA B2B, Virtual Account
* Yodlee Fastlink API

* Xcode 9 / Swift 4+
* iOS 10.0+
* Java 8+

### Installing

Java
```bash
mvn clean package
```

Xcode
Then, run the following command:

```bash
$ pod install
```


## The How
* The app creates a virtual account linked to your credit/debit/prepaid visa account. 
* The limits are set at the Merchant Category Code levels
* Once limit is reached the envelope is disabled.
* Any additional money left in any of the envelopes at the end of the month is distributed to your short term goals first *then* to your long term financial goals savings for college, buying house,  upgrading a car, a fine dress, a fancy ring, a dream vacation, …etc

## The Why
* The average American household carries $137,063 in debt,
* 1 in 2 families in the US can’t come up with $400 for emergency 
* 51M adults in the US are underbanked, 45M lack credit score
* Most families have no idea how are they spending their money

## Future Enhancements:
* Bio/OTP Registration & Login
* Suggested categories by our ML algorithm. 
* Enable Prepaid cards for those who doesn't have bank acct
* Native Wallet Integration
* AI based new categories/savings suggestions

## Deployment

AWS Elastic Beanstalk automation with CICD pipeline.

## Built With

* [Swift](https://developer.apple.com/swift/)
* [Java](https://www.java.com/en/download/) 
* [Visa Developer API](https://developer.visa.com/) 
* [Yodlee API](https://developer.yodlee.com/)

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

0.0.1

## Authors

* **Amro** - *Initial work* - [smilar](https://github.com/smilar)
* **Andrey** - *Initial work* - [andreydanil](https://github.com/andreydanil)
* **Nick** - *Initial work* - [nickChaseUser](https://github.com/nickChaseUser)
* **Kumar** - *Initial work* - [kumar-sundaram](https://github.com/kumar-sundaram)


See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

