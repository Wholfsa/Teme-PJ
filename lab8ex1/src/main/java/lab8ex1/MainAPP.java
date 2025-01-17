package lab8ex1;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.management.remote.JMXConnectorFactory.connect;

// Clase pentru excepții personalizate
class ExceptieVarsta extends Exception {
    public ExceptieVarsta(String message) {
        super(message);
    }
}

class ExceptieAnExcursie extends Exception {
    public ExceptieAnExcursie(String message) {
        super(message);
    }
}
public class MainAPP {


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lab8ex1";
        Connection conn;

        {
            try {
                conn = DriverManager.getConnection(url, "root", "Mamatata0!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("\nMeniu:");
                System.out.println("1. Adăugare persoană");
                System.out.println("2. Adăugare excursie");
                System.out.println("3. Afișare persoane și excursii");
                System.out.println("4. Afișare excursii după nume persoană");
                System.out.println("5. Afișare persoane care au vizitat o destinație");
                System.out.println("6. Afișare persoane cu excursii într-un an");
                System.out.println("7. Ștergere excursie");
                System.out.println("8. Ștergere persoană");
                System.out.println("9. Ieșire");

                System.out.print("Alege o opțiune: ");
                int optiune = scanner.nextInt();
                scanner.nextLine(); // Consumă newline

                switch (optiune) {
                    case 1:
                        adaugaPersoana(conn, scanner);
                        break;
                    case 2:
                        adaugaExcursie(conn, scanner);
                        break;
                    case 3:
                        afiseazaPersoaneSiExcursii(conn);
                        break;
                    case 4:
                        afiseazaExcursiiDupaNume(conn, scanner);
                        break;
                    case 5:
                        afiseazaPersoaneDupaDestinatie(conn, scanner);
                        break;
                    case 6:
                        afiseazaPersoaneDupaAn(conn, scanner);
                        break;
                    case 7:
                        stergeExcursie(conn, scanner);
                        break;
                    case 8:
                        stergePersoana(conn, scanner);
                        break;
                    case 9:
                        running = false;
                        System.out.println("Zi buna");
                        break;
                    default:
                        System.out.println("Opțiune invalidă!");
                }
            }
        }




    private static void adaugaPersoana(Connection conn, Scanner scanner) {
        try {
            System.out.print("Introduceți numele persoanei: ");
            String nume = scanner.nextLine();

            System.out.print("Introduceți vârsta persoanei: ");
            int varsta = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            if (varsta < 0 || varsta > 120) {
                throw new ExceptieVarsta("Vârsta trebuie să fie între 0 și 120.");
            }

            String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nume);
                stmt.setInt(2, varsta);
                stmt.executeUpdate();
                System.out.println("Persoană adăugată cu succes!");
            }
        } catch (ExceptieVarsta e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaExcursie(Connection conn, Scanner scanner) {
        try {
            System.out.print("Introduceți ID-ul persoanei: ");
            int idPersoana = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            String sqlCheck = "SELECT COUNT(*) FROM persoane WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {
                checkStmt.setInt(1, idPersoana);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    System.out.println("Persoana nu există în baza de date.");
                    return;
                }
            }

            System.out.print("Introduceți destinația excursiei: ");
            String destinatia = scanner.nextLine();

            System.out.print("Introduceți anul excursiei: ");
            int anul = scanner.nextInt();
            scanner.nextLine(); // Consumă newline

            String sqlAn = "SELECT varsta FROM persoane WHERE id = ?";
            try (PreparedStatement anStmt = conn.prepareStatement(sqlAn)) {
                anStmt.setInt(1, idPersoana);
                ResultSet rs = anStmt.executeQuery();
                rs.next();
                int varsta = rs.getInt(1);
                int anulNasterii = 2025 - varsta;
                if (anul < anulNasterii || anul > 2025) {
                    throw new ExceptieAnExcursie("Anul excursiei trebuie să fie între anul nașterii și anul curent.");
                }
            }

            String sql = "INSERT INTO excursie (id_persoana, destinatie, anul) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPersoana);
                stmt.setString(2, destinatia);
                stmt.setInt(3, anul);
                stmt.executeUpdate();
                System.out.println("Excursie adăugată cu succes!");
            }
        } catch (ExceptieAnExcursie e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneSiExcursii(Connection conn) {
        String sql = "SELECT p.id, p.nume, p.varsta, e.destinatie, e.anul FROM persoane p LEFT JOIN excursie e ON p.id = e.id_persoana";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                String destinatia = rs.getString("destinatie");
                int anul = rs.getInt("anul");

                System.out.printf("ID: %d, Nume: %s, Vârsta: %d, Destinație: %s, An: %d\n",
                        id, nume, varsta, destinatia == null ? "N/A" : destinatia, anul);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaExcursiiDupaNume(Connection conn, Scanner scanner) {
        System.out.print("Introduceți numele persoanei: ");
        String nume = scanner.nextLine();

        String sql = "SELECT e.destinatie, e.anul FROM excursie e JOIN persoane p ON e.id_persoana = p.id WHERE p.nume = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nume);
            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                String destinatia = rs.getString("destinatie");
                int anul = rs.getInt("anul");
                System.out.printf("Destinație: %s, An: %d\n", destinatia, anul);
            }
            if (!found) {
                System.out.println("Nicio excursie găsită pentru persoana specificată.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void afiseazaPersoaneDupaDestinatie(Connection conn, Scanner scanner) {
        System.out.print("Introduceți destinația: ");
        String destinatia = scanner.nextLine();

        String sql = "SELECT DISTINCT p.id, p.nume, p.varsta FROM persoane p " + "JOIN excursie e ON p.id = e.id_persoana WHERE e.destinatie = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, destinatia);
            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            System.out.println("Persoane care au vizitat " + destinatia + ":");
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                System.out.printf("ID: %d, Nume: %s, Vârsta: %d\n", id, nume, varsta);
            }
            if (!found) {
                System.out.println("Nicio persoană nu a vizitat destinația specificată.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void afiseazaPersoaneDupaAn(Connection conn, Scanner scanner) {
        System.out.print("Introduceți anul: ");
        int anul = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        String sql = "SELECT DISTINCT p.id, p.nume, p.varsta FROM persoane p " +
                "JOIN excursie e ON p.id = e.id_persoana WHERE e.anul = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, anul);
            ResultSet rs = stmt.executeQuery();
            boolean found = false;
            System.out.println("Persoane care au făcut excursii în anul " + anul + ":");
            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                int varsta = rs.getInt("varsta");
                System.out.printf("ID: %d, Nume: %s, Vârsta: %d\n", id, nume, varsta);
            }
            if (!found) {
                System.out.println("Nicio persoană nu a făcut excursii în anul specificat.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void stergeExcursie(Connection conn, Scanner scanner) {
        System.out.print("Introduceți ID-ul excursiei de șters: ");
        int idExcursie = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        String sql = "DELETE FROM excursie WHERE id_excursie = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idExcursie);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Excursia a fost ștearsă cu succes.");
            } else {
                System.out.println("Excursia nu a fost găsită.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void stergePersoana(Connection conn, Scanner scanner) {
        System.out.print("Introduceți ID-ul persoanei de șters: ");
        int idPersoana = scanner.nextInt();
        scanner.nextLine(); // Consumă newline

        try {
            conn.setAutoCommit(false); // Start tranzacție

            String sqlExcursii = "DELETE FROM excursie WHERE id_persoana = ?";
            try (PreparedStatement stmtExcursii = conn.prepareStatement(sqlExcursii)) {
                stmtExcursii.setInt(1, idPersoana);
                stmtExcursii.executeUpdate();
            }

            String sqlPersoana = "DELETE FROM persoane WHERE id = ?";
            try (PreparedStatement stmtPersoana = conn.prepareStatement(sqlPersoana)) {
                stmtPersoana.setInt(1, idPersoana);
                int rowsDeleted = stmtPersoana.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Persoana și excursiile asociate au fost șterse cu succes.");
                } else {
                    System.out.println("Persoana nu a fost găsită.");
                }
            }

            conn.commit(); // Finalizează tranzacția
        } catch (SQLException e) {
            try {
                conn.rollback(); // Revocă tranzacția în caz de eroare
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Revine la modul implicit
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


