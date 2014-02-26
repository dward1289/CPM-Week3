//
//  AddViewController.m
//  ActiveList
//
//  Created by Devona Ward on 2/17/14.
//  Copyright (c) 2014 Devona Ward. All rights reserved.
//

#import "AddViewController.h"
#import <Parse/Parse.h>


@interface AddViewController ()

@end

@implementation AddViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    //Set current date for date label
    NSDate *currentDate = [NSDate date];
    NSDateFormatter *dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"MMMM d, yyyy"];
    dateTxt.text = [dateFormat stringFromDate:currentDate];
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"ActiveLog" message: @"Complete all fields before saving, or an error will occur." delegate: nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alert show];
}

//Dismisses keyboard when "Done" is pressed
- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [textField resignFirstResponder];
    return NO;
}

//Get date from date picker
-(IBAction)onDate:(id)sender{
    NSDate *date = theDate.date;
    NSDateFormatter *dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"M/d/yyyy"];
    dateTxt.text = [dateFormat stringFromDate:date];
}

//Limits text for minutes
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string
{
    return textField.text.length + (string.length - range.length) <= 4;
}


//Save entry to Parse database
-(IBAction)saveItem:(id)sender{
    wrbTxt = [walkRun titleForSegmentAtIndex:walkRun.selectedSegmentIndex];
    rateTxt = [rateIt titleForSegmentAtIndex:rateIt.selectedSegmentIndex];
    dateEntered = dateTxt.text;
    minuteEntered = minuteTxt.text;
    milesEntered = milesTxt.text;
    int mE = milesEntered.intValue;
    
    PFObject *savedObject = [PFObject objectWithClassName:@"TheLog"];
    savedObject[@"Date"] = dateEntered;
    savedObject[@"Exercise"] = wrbTxt;
    savedObject[@"Time"] = minuteEntered;
    savedObject[@"Miles"] = [NSNumber numberWithInt:mE];
    savedObject[@"RYE"] = rateTxt;
    [savedObject saveInBackground];
    
    //Confirms the save has been completed
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Success!" message: @"ActiveLog entry saved. Refresh to update your ActiveLog list." delegate: nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alert show];
    
    [self dismissViewControllerAnimated:YES completion:nil];
    
    
}


//Go back to main screen
-(IBAction)onCancel:(id)sender{
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
