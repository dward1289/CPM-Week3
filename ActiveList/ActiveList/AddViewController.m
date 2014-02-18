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
    theDate.hidden = TRUE;
    theTime.hidden = TRUE;

    
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
}

//Save entry
-(IBAction)saveItem:(id)sender{
    
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
