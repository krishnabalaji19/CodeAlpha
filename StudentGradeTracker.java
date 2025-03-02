import java.util.*;
public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> scores = new ArrayList<>();
        System.out.println("Welcome to the Student Grade Tracker!");
        int n = 0;
        while(n<=0) {
	        System.out.println("Enter the number of subjects : ");
	        n = scanner.nextInt();
	        if(n<=0) { 
	        	System.out.println("Enter valid number of subjects try again!");
	        	continue;
	        }
        }
        System.out.println("Enter the data : ");
        int i=0;
        double x;
        if(n>0) {
	        while(i<n) {
	        	x = scanner.nextDouble();
	        	if(x>0 && x<=100) {
	        		scores.add(x);
	        		i++;
	        	}
	        	else {
	        		System.out.println("Invalid subject's score enter a valid one");
	        	}
	        }
        }
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Compute sum and give the Grade");
            System.out.println("2. Compute average score");
            System.out.println("3. Find highest score");
            System.out.println("4. Find lowest score");
            System.out.println("5. Exit");
            int choice;
            try {
                choice = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1:
                    computeSumAndGrade(scores,n);
                    break;
                case 2:
                    computeAverage(scores);
                    break;
                case 3:
                    findHighestGrade(scores);
                    break;
                case 4:
                    findLowestGrade(scores);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
    
    public static void computeSumAndGrade(ArrayList<Double> scores,int n) {
        double sum = 0.0;
        for(double i:scores) {
        	sum+=i;
        }
        double total = n*100.0;
        double percentage = (sum/total)*100.0;
        if(percentage>=90) {
        	System.out.println("Sum of scores out of "+total+" is : "+sum+" and secured Destinction");
        }
        else if(percentage>=80 && percentage<90) {
        	System.out.println("Sum of scores out of "+total+" is : "+sum+" and secured First class");
        }
        else if(percentage>=65 && percentage<80) {
        	System.out.println("Sum of scores out of "+total+" is : "+sum+" and secured Second class");
        }
        else if(percentage>=40 && percentage<65) {
        	System.out.println("Sum of scores out of "+total+" is : "+sum+" and secured Third class");
        }
        else {
            System.out.println("Sum of scores out of "+total+" is : "+sum+" and the grade is Fail");
        }
    }
    public static void computeAverage(ArrayList<Double> scores) {
        if (scores.isEmpty()) {
            System.out.println("No grades entered yet.");
            return;
        }

        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        double average = sum / scores.size();
        System.out.printf("The average grade is: %.2f\n", average);
    }

    public static void findHighestGrade(ArrayList<Double> grades) {
        if (grades.isEmpty()) {
            System.out.println("No grades entered yet.");
            return;
        }

        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        System.out.printf("The highest grade is: %.2f\n", highest);
    }

    public static void findLowestGrade(ArrayList<Double> grades) {
        if (grades.isEmpty()) {
            System.out.println("No grades entered yet.");
            return;
        }

        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        
        System.out.printf("The lowest grade is: %.2f\n", lowest);
    }
}
