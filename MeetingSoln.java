import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MeetingSoln {

    public  static void solve(){
    
        class Info{
            int meetingDay,left , right,level;
            Info(int m,int l,int r,int level){
                meetingDay=m;
                left=l;
                right=r;
                this.level=level;


            }
            
        }
        int meetingDays[]={0,3,14,18,21,30};
        int totalMeetings=5; //total meetings scheduled
        int k=3; //compulsory meetings
        int leaves = 30;

        int considerdUpTo[] = new int[totalMeetings+1];
        LinkedList<Info> queue = new LinkedList<>();
        Map<Integer,Integer> position = new HashMap<>();

        int answer=0;

        for(int i=1;i<=totalMeetings;i++){
            position.put(meetingDays[i], i);
            considerdUpTo[i]=0;
        }

        for(int i=1;i<=totalMeetings-k+1;i++){
            int left = meetingDays[i]-1;
            int right = leaves - meetingDays[i];

            if(left>=right){
                if(left>answer)
                    answer = left;
            }else{
                queue.addLast(new Info(meetingDays[i],left,right,1));
            }

            considerdUpTo[i]=1;
            
        }


        while(!queue.isEmpty()){
            Info currInfo = queue.poll();

            int index = position.get(currInfo.meetingDay);

            int meetingsDone = currInfo.level;

            if(meetingsDone==k){
                if(currInfo.right>answer){
                    answer=currInfo.right;
                }
                continue;
            }
            
            for(int i=index+1;i<=totalMeetings;i++){
                int meetingRemaining = totalMeetings-i+1;

                //OBSERVATION: 1
                if((meetingRemaining+meetingsDone)<k){
                    //do not consider for examinaiton as obligation will not be met.
                    continue;
                }

                //OBSERVATION: 2 & 3
                int left = meetingDays[i]-currInfo.meetingDay-1;
                int right = currInfo.right-left-1;

                //OBSERVATION : 4
                if(left>=right){
                    answer = (left>answer)?left:answer;
                    continue;
                }

                //OBSERVATION : 5
                if(considerdUpTo[i]>=(currInfo.level+1))continue;

                considerdUpTo[i]=currInfo.level+1;
                queue.addLast(new Info(meetingDays[i], left, right, meetingsDone+1));

            }

            
        }

        System.out.println("Contiguous days :"+answer);

    }
    

    public static void main(String[] args) {
        solve();
    }
    
}
