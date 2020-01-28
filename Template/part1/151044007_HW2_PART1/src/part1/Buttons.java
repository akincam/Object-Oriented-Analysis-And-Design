package part1;

/**
 * Buttons enum type
 * Sets name of value
 */
enum  Buttons {
    Start , Stop , Pause,Run;

    public String toString(){
        String return_type;
        switch (this){
            case Start:
                return_type = "Start";
                break;
            case Stop:
                return_type = "Stop";
                break;
            case Pause:
                return_type = "Pause";
                break;
            case Run:
                return_type = "Run";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
        return return_type;
    }
}
