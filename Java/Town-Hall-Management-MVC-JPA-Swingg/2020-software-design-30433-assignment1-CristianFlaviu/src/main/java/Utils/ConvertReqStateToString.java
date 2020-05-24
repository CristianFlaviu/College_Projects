package Utils;

public  class ConvertReqStateToString {

    public static String convert(RequestState requestState)
    {
        if(requestState==RequestState.NOTApproved)
            return new String("Not Approved");
        else if(requestState==RequestState.Approved)
            return  new String("Approved");
        else if(requestState==RequestState.NotChecked)
            return  new String("Not Checked");
        return null;
    }
}
