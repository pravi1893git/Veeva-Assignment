

@cp
Feature: To test functionality of Core Product (CP)

  @tc1
  Scenario: Get product details from Warrior Mens webpage and attach it as a text file in the report
    Given User invokes the "nba-warriors" url
    When User navigates to "Shop" -> "Men's"
    And User selects Jackets from All Departments
    Then User stores Price, Title and Top Seller message from each products to a text file and attach to report   
    
	@tc1
	Scenario: Get counts of total Videos Feeds and the video feeds those are present in the page >= 3d
		Given User invokes the "nba-warriors" url
		When User navigates to ... -> News And Features
		Then User verifies the count of total video feeds shown
		And User verifies the count of video feeds those are present in the page greater than or equal to "3"d
