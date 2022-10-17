package de.jo.supa4j.api;

public enum SupabaseOperators {

    EQUALS("eq"), NOT_EQUALS("neq"), GREATER_THAN("gt"), GREATER_THAN_EQUALS("gte"), LESS_THAN("lt"), LESS_THAN_EQUALS("lte"),
    LIKE_PATTERN_CASE_SENSITIVE("like"), LIKE_PATTERN_CASE_INSENSITIVE("ilike"), IS("is"), IN("in"), CONTAINS("contains"),
    CONTAINED_BY("containedBy"), RANGE_GREATER_THAN("rangeGt"), RANGE_GREATER_THAN_EQUALS("rangeGte"), RANGE_LESS_THAN("rangeLt"),
    RANGE_LESS_THAN_EQUALS("rangeLte"), RANGE_ADJACENT("rangeAdjacent"), OVERLAPS("overlaps"), TEXT_SEARCH("textSearch"),
    MULTI_EQUALS("match"), NOT("not"), OR("or"), FILTER("filter");

    private String op;
    SupabaseOperators(String op) {
        this.op = op;
    }
    @Override
    public String toString() {
        return "="+op+".";
    }
}
