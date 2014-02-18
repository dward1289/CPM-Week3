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
    IBOutlet UIBarButtonItem *doneBtn;
    IBOutlet UIBarButtonItem *editBtn;
}

-(IBAction)addNew:(id)sender;
@end
