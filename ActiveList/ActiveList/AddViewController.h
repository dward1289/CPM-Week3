//
//  AddViewController.h
//  ActiveList
//
//  Created by Devona Ward on 2/17/14.
//  Copyright (c) 2014 Devona Ward. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AddViewController : UIViewController
{
    IBOutlet UIDatePicker *theDate;
    IBOutlet UISegmentedControl *rateIt;
    IBOutlet UISegmentedControl *wrb;
    IBOutlet UITextField *dateTxt;
    IBOutlet UITextField *milesTxt;
    IBOutlet UITextField *timeTxt;

}

-(IBAction)saveItem:(id)sender;
-(IBAction)onCancel:(id)sender;
-(IBAction)onDate:(id)sender;
@end
