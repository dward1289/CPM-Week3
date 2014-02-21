//
//  ViewController.m
//  ActiveList
//
//  Created by Devona Ward on 2/17/14.
//  Copyright (c) 2014 Devona Ward. All rights reserved.
//

#import "ViewController.h"
#import "AddViewController.h"


@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    
	// Do any additional setup after loading the view, typically from a nib.
    
    theDated = [[NSMutableArray alloc]init];
    theExercise = [[NSMutableArray alloc]init];
    theTimes = [[NSMutableArray alloc]init];
    theMiles = [[NSMutableArray alloc]init];
    theRate = [[NSMutableArray alloc]init];
    ALLDATA = [[NSMutableArray alloc]init];
    displayString = [[NSMutableString alloc]init];
    
    //Get data from Parse.com
    PFQuery *query = [PFQuery queryWithClassName:@"TheLog"];
    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
        if (!error) {
            for (PFObject *object in objects) {
                
                [theDated addObject:[object valueForKey:@"Date"]];
                [theExercise addObject:[object valueForKey:@"Exercise"]];
                [theTimes addObject:[object valueForKey:@"Time"]];
                [theMiles addObject:[object valueForKey:@"Miles"]];
                [theRate addObject:[object valueForKey:@"RYE"]];
            }
            //Add data to proper nsmutable arrays
            for (int i=0; i<[theDated count]; i++) {
                dateTxt = [theDated objectAtIndex:i];
                eTxt = [theExercise objectAtIndex:i];
                timeTxt = [theTimes objectAtIndex:i];
                mileTxt = [theMiles objectAtIndex:i];
                rateTxt = [theRate objectAtIndex:i];
                //Display data in a string
                allData = [[NSString alloc]initWithFormat:@"Date: %@\n Exercise: %@\n Time: %@\n Miles: %@\n Exercise Rate: %@\n\n", dateTxt,eTxt,timeTxt,mileTxt,rateTxt];
                //Add string to array
                [ALLDATA addObject:allData];
                
                [displayString appendString:[NSString stringWithFormat:@"%@",allData]];
            }
            //Display a list of the data
            dataHere.text = displayString;
            
            //Store data in a plist file
            NSArray *paths = NSSearchPathForDirectoriesInDomains
            (NSDocumentDirectory, NSUserDomainMask, YES);
            NSString *documentsDirectory = [paths objectAtIndex:0];
            NSString *fileName = [NSString stringWithFormat:@"%@/theDataFile.plist",
                                  documentsDirectory];
            
            [ALLDATA writeToFile:fileName atomically:YES];
            
        } else {
            //Log errors
            NSLog(@"Error: %@ %@", error, [error userInfo]);
        }
    }];
    
    
}

//Refresh list
-(IBAction)onRefresh:(id)sender{
    if ([self isViewLoaded]) {
        
        [self viewDidLoad];
    }
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"ActiveLog" message: @"ActiveLog list has been updated." delegate: nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
    [alert show];
}

//Add new entry
-(IBAction)addNew:(id)sender{
    AddViewController *addEntry = [[AddViewController alloc]init];
    [self presentViewController:addEntry animated:YES completion:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
