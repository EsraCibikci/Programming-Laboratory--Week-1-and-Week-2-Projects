
import java.util.Scanner;

public class ProgrammingLab1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] teams = {"Team A", "Team B", "Team C", "Team D"};
        int[] wins = new int[4];
        int[] draws = new int[4];
        int[] losses = new int[4];
        int[] points = new int[4];
        int[] goalsFor = new int[4];
        int[] goalsAgainst = new int[4];

        int[] team1 = {0, 2, 0, 1, 0, 1};
        int[] team2 = {1, 3, 2, 3, 3, 2};

        System.out.println("=== FIXTURE ===");
        for (int i = 0; i < 6; i++) {
            int t1 = team1[i];
            int t2 = team2[i];
            System.out.println("Match " + (i + 1) + ": " + teams[t1] + " vs " + teams[t2]);
        }
        System.out.println("-----------------------------------");

        for (int i = 0; i < 6; i++) {
            int t1 = team1[i];
            int t2 = team2[i];

            System.out.println("\nMatch " + (i + 1) + ": " + teams[t1] + " vs " + teams[t2]);
            System.out.print(teams[t1] + " goals: ");
            int g1 = scan.nextInt();
            System.out.print(teams[t2] + " goals: ");
            int g2 = scan.nextInt();

            goalsFor[t1] += g1;
            goalsAgainst[t1] += g2;
            goalsFor[t2] += g2;
            goalsAgainst[t2] += g1;

            if (g1 > g2) {
                points[t1] += 3;
                wins[t1]++;
                losses[t2]++;
            } else if (g2 > g1) {
                points[t2] += 3;
                wins[t2]++;
                losses[t1]++;
            } else {
                points[t1] += 1;
                points[t2] += 1;
                draws[t1]++;
                draws[t2]++;
            }
        }

        System.out.println("\n=== STANDINGS TABLE ===");
        System.out.println("Team\tMP\tW\tD\tL\tGD\tPTS");
        for (int i = 0; i < 4; i++) {
            int gd = goalsFor[i] - goalsAgainst[i];
            System.out.println(teams[i] + "\t3\t" + wins[i] + "\t" + draws[i] + "\t" + losses[i] + "\t" + gd + "\t" + points[i]);
        }

        int champIndex = 0;

        for (int i = 1; i < 4; i++) {
            int currentGD = goalsFor[i] - goalsAgainst[i];
            int champGD = goalsFor[champIndex] - goalsAgainst[champIndex];

            if (points[i] > points[champIndex]) {
                champIndex = i;
            } else if (points[i] == points[champIndex] && currentGD > champGD) {
                champIndex = i;
            }
        }

        System.out.println("\nTournament Champion: " + teams[champIndex]);

    }
}
