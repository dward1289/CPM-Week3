//
//  ViewController.h
//  ActiveList
//
//  Created by Devona Ward on 2/17/14.
//  Copyright (c) 2014 Devona Ward. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Parse/Parse.h>

@interface ViewController : UIViewController 
{
    IBOutlet UITextView *dataHere;
    NSString *dateTxt;
    NSString *eTxt;
    NSString *timeTxt;
    NSString *mileTxt;
    NSInteger *milesNum;
    NSString *rateTxt;
    NSString *allData;
    
    NSMutableArray *theDated;
    NSMutableArray *theExercise;
    NSMutableArray *theTimes;
    NSMutableArray *theMiles;
    NSMutableArray *theRate;
    NSMutableArray *ALLDATA;
    NSMutableString *displayString;
    
    
}

-(IBAction)addNew:(id)sender;
-(IBAction)onRefresh:(id)sender;
@end
