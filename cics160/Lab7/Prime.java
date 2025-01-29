class Prime {
    public static void main(String[] args) {

    }

    public static boolean isPrime(int n){
        int nextFactorToCheck = 2;
        if (n < 2) return (false);
        while (nextFactorToCheck < n) {
            if (n%nextFactorToCheck == 0){
                return (false);
            }
            nextFactorToCheck = nextFactorToCheck + 1;
        }
        return(true);
    }
}