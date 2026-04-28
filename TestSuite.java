public class TestSuite {
    public static void main(String[] args) throws Exception {
        int passed = 0;
        int failed = 0;

        System.out.println("====================================");
        System.out.println("     SELENIUM TEST SUITE STARTED");
        System.out.println("====================================\n");

        // Test 1: Login Test
        try {
            System.out.println("[1] Running Login Test...");
            Main.main(new String[] {});
            passed++;
            System.out.println("Status: PASSED\n");
        } catch (Exception e) {
            failed++;
            System.out.println("Status: FAILED - " + e.getMessage() + "\n");
        }

        // Test 2: Dropdown Test
        try {
            System.out.println("[2] Running Dropdown Test...");
            DropdownTest.main(new String[] {});
            passed++;
            System.out.println("Status: PASSED\n");
        } catch (Exception e) {
            failed++;
            System.out.println("Status: FAILED - " + e.getMessage() + "\n");
        }

        // Test 3: Alerts Test
        try {
            System.out.println("[3] Running Alerts Test...");
            AlertsTest.main(new String[] {});
            passed++;
            System.out.println("Status: PASSED\n");
        } catch (Exception e) {
            failed++;
            System.out.println("Status: FAILED - " + e.getMessage() + "\n");
        }

        // Test 4: Table Test
        try {
            System.out.println("[4] Running Table Test...");
            TableTest.main(new String[] {});
            passed++;
            System.out.println("Status: PASSED\n");
        } catch (Exception e) {
            failed++;
            System.out.println("Status: FAILED - " + e.getMessage() + "\n");
        }

        // Test 5: File Upload Test
        try {
            System.out.println("[5] Running File Upload Test...");
            FileUploadTest.main(new String[] {});
            passed++;
            System.out.println("Status: PASSED\n");
        } catch (Exception e) {
            failed++;
            System.out.println("Status: FAILED - " + e.getMessage() + "\n");
        }

        // Summary
        System.out.println("====================================");
        System.out.println("     TEST SUITE SUMMARY");
        System.out.println("====================================");
        System.out.println("Total Tests: " + (passed + failed));
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("====================================");
        System.out.println("     TEST SUITE COMPLETED");
        System.out.println("====================================");
    }
}
