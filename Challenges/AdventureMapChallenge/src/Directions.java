public enum Directions {
    N,
    E,
    S,
    W;

    public String getDirection(){
        return new String[]{"North", "East", "South", "West"}[this.ordinal()];
    }
}
