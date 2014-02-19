//
//  AddViewController.m
//  ActiveList
//
//  Created by Devona Ward on 2/17/14.
//  Copyright (c) 2014 Devona Ward. All rights reserved.
//

#import "AddViewController.h"


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
    [dateFormat setDateFormat:@"MMMM d, yyyy"];
    dateTxt.text = [dateFormat stringFromDate:date];
}

//Limits text for minutes and seconds
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string
{
    return textField.text.length + (string.length - range.length) <= 2;
}

//Save entry
-(IBAction)saveItem:(id)sender{
    wrbTxt = [walkRun titleForSegmentAtIndex:walkRun.selectedSegmentIndex];
    rateTxt = [rateIt titleForSegmentAtIndex:rateIt.selectedSegmentIndex];
    dateEntered = dateTxt.text;
    minuteEntered = minuteTxt.text;
    secondEntered = secondTxt.text;
    milesEntered = milesTxt.text;
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
