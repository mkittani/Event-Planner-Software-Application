package wedding.Planner;

    public class Package {
        private String name;
        private double cost;

        public Package(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        // Getters
        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }



        @Override
        public String toString() {
            return "Package => " + name + "  Cost=" + cost;
        }
    }


