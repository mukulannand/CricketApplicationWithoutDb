enum teamName {
    India,
    Australia,
    NewZealand,
    England,
    Pakistan,
    SouthAfrica,
    Bangladesh,
    SriLanka,
    WestIndies,
    Afganistan;

    public  int teamID ( teamName team )
    {
        return team.ordinal ();
    }
}
