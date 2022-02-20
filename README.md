# Sparta Trainee Simulation

## How the project works

The simulation can take in a number of months, and randomly generate information on trainees, centers, and clients each month. Each month, it will generate trainees, and every two months it will generate clients. After a year of training, it will start generating clients that will take the trainees and put them to work. If the clients get all the trainees they want they will return, otherwise they will leave unhappy.
At the end of the simulation, you will be able to see information on each of the generated classes.


## How to use the project
Run the simulation controller main method, you will be met with two inputs. The first is the number of months you want the program can run for. There is no upper limit for the months. The second is you can request either all the information at the end or information as the program runs. After that there is nothing else needed, just let the program run and you can see the results at the end. You can tweak the random numbers to test how it will work under different situations.

## Teams
Stephen - Scrum Master
Brandon - Developer
Harry - Developer
Natasha - Developer
Mehmet  - Tester
Ben - Tester

## Sprints
### Sprint 1
The first sprint was making the core loop for the project. We created a Trello board to keep track of what we were working on and what was coming up and we started drafting a testing plan.
#### Sprint review
It took longer than expected, and due to this, we had to adapt the future sprints around this. We were not happy with the rate of this sprint, but understanding the scale of the project made it difficult to know how to adapt.

### Sprint 2
The main core loop was created but it was not ready to be tested, so refactoring took place for this sprint. During this stage, we set up an MCV model and started outputting the information.
#### Sprint review
Due to the day before we chose a smaller sprint for sprint 2. This was difficult to get done, and not all of it was done in the end. This was better than the first sprint, but again was not great as we were will trying to understand how to implement the different parts of the project
#### Sprint 3
Incorporating the new information from day 2 into the loop and the refactoring was the main aim of the sprint. Testing began on the main core loop. The information given from day 3 was started on but not added to the main loop yet.
#### Sprint review
This sprint was much better, just focusing on getting the refactoring while one person worked on creating the classes and methods for the new information. At this point, we had started to work out all the different issues, and as we started feeling better about it things started moving faster.

### Sprint 4
Adding in the information from day 3 into the main loop was the main part of the day, as well as testing it and improving testing on the previous information. This was the main sprint for cleaning up the code and making small improvements.
#### Sprint review
As sprint 3 went much better, sprint 4 also went well. The sprint was not a large number of things to do and we got through them at a good pace. With one or two exceptions, this was just for cleaning up and as such with not a lot of pressure it was a more relaxed sprint.


## Epics 
We create 4 different epics for each part of the project that we can then use to create user stories, and then made our acceptance criteria based on that.
### Inputs
As a user, I want to be able to input the number of months for the program to run.
##### Acceptance Criteria
The program must be able to accept a number of months that will tell the program how long to run for.

### Trainees
As a user, I want the system to generate trainees and train them in a course.

As a user, I want trainees to have a waiting list once trained to move to clients

As a user, if a center closes I want the trainees to be able to move to a new one

#### Acceptance Criteria
Trainees are generated for each center

Trainees are assigned and trained in one course

Trainees are turned to trained after a year and moved to wait

Trainees are moved to the client after a year

Trainees are added to a waiting list if there is no place for them to go yet

### Centers
I want a random center to be made every two months

I want the center to take a number of trainees each month up to their capacity

I want the center to be able to take trainees from the waiting list

I want three different types of centers to be available with different specifications

I want the center to close if the numbers are low

#### Acceptance Criteria
A center is made every two months

The center will be either a training hub, Bootcamp or tech center

Training hubs have a capacity of 100 and can open 3 at a time(max)

Bootcamp can train 500 and wait 3 months before closing to low attendance - only two can exist at a time

The Tech center only teaches one course with 200 trainees. Chosen randomly.

Center is closed if it goes below 25 users after the first month

Center moves all its students to the waiting list if it closes

### Clients
As a user:
I want Clients to start being generated after a year at a 50% chance

I want the client to be looking for students with specific training.

I want the client to be looking for at least 16 trainees

I want the client to keep taking trainees until they hit their required amount

I want the client to leave unhappy if they don't get them after a year

I want the client to leave happy if they get their amount, and return the year after for more.

#### Acceptance Criteria
Clients aren’t generated till after the first year

Each month there is a 50% chance of being created

Each client picks a random amount, starting at 16 trainees to take

After a year they will stop taking and leave

They should leave happy if they have enough students

They should leave unhappy if they don’t reach their max

## Behavior-driven development

In this project, BDD approach is used to test the program in different scenarios. Cucumber tool and gherkin language were also added to be able to do that. There were several impediments in this testing due to the nature of program. There is not many interactions in the program for user, but since BDD approach is more similar to black-box testing, what has been done in this program looks like white box testing more. 

![image-ResultsOfTrainingCentre](https://i.imgur.com/a27JBHF.png)

![image-ResultsOfWaitingList](https://i.imgur.com/durbYEn.png)

## Review
Overall we are happy with the outcome of the project. There are some improvements we would make given the opportunity, but this would be mostly to our process. Given more time, we would spend more time formally reviewing and going over each sprint, which would help us improve in the future.

