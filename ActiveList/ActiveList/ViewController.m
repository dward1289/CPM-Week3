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
            
            for (int i=0; i<[theDated count]; i++) {
                dateTxt = [theDated objectAtIndex:i];
                eTxt = [theExercise objectAtIndex:i];
                timeTxt = [theTimes objectAtIndex:i];
                mileTxt = [theMiles objectAtIndex:i];
                rateTxt = [theRate objectAtIndex:i];
                
                allData = [[NSString alloc]initWithFormat:@"Date: %@\r Exercise: %@\r Time: %@\r Miles: %@\r Exercise Rate: %@\r\r", dateTxt,eTxt,timeTxt,mileTxt,rateTxt];
                
                [ALLDATA addObject:allData];
                
                [displayString appendString:[NSString stringWithFormat:@"%@",allData]];
            }
            dataHere.text = displayString;
            
            //Stores data locally
            NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
            NSString *documentsDirectory = [paths objectAtIndex:0];
            NSString *filePath = [documentsDirectory stringByAppendingString:@"theInfo.txt"];
            [ALLDATA writeToFile:filePath atomically:YES];
            
            
            
        } else {
            // Log details of the failure
            NSLog(@"Error: %@ %@", error, [error userInfo]);
        }
    }];
    
}




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
