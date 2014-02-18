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
