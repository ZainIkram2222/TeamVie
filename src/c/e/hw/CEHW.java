package c.e.hw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Person {
    String id;
    String firstName;
    String lastName;
    String email;

    // Constructor to initialize a Person object
    public Person(String firstName, String lastName, String id, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        
    }
}

class Team {
    String name;
    List<Person> members = new ArrayList<>();

    // Constructor to initialize a Team object
    public Team(String name) {
        this.name = name;
    }
}

public class CEHW {
    public static void main(String[] args) {
        String filePath = "MOCK_DATA.csv"; // Replace with the actual path to your data file

        // Read data from the file
        List<Person> people = readDataFromFile(filePath);

        int numTeams = 20;
        int teamSize = 5;

        // Create teams with random members
        List<Team> teams = createTeams(people, numTeams, teamSize);

        // Print information about each team
        for (Team team : teams) {
            System.out.println("Team Name: " + team.name);
            System.out.println("Team Members:");
            for (Person member : team.members) {
                System.out.println(member.firstName + " " + member.lastName + " (" + member.email + ")");
            }
            System.out.println();
        }
    }

    // Read data from a CSV file and return a list of Person objects
    public static List<Person> readDataFromFile(String filePath) {
        List<Person> people = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Create a Person object and add it to the list
                    Person person = new Person(parts[0], parts[1], parts[2], parts[3]);
                    people.add(person);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }

    // Create teams with random members
    public static List<Team> createTeams(List<Person> people, int numTeams, int teamSize) {
        List<Team> teams = new ArrayList<>();
        List<Person> remainingPeople = new ArrayList<>(people);
        Random random = new Random();

        for (int i = 1; i <= numTeams; i++) {
            Team team = new Team("Team " + i);
            Collections.shuffle(remainingPeople);

            for (int j = 0; j < teamSize; j++) {
                // Add random team members to the team
                Person person = remainingPeople.remove(0);
                team.members.add(person);
            }

            teams.add(team);
        }
        return teams;
    }
}
