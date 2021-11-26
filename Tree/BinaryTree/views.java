import java.util.Queue;

// LEFT VIEW
public List<Integer>leftSideView(TreeNode root){if(root==null)return new ArrayList<>();Queue<TreeNode>que=new LinkedList<>();que.add(root);List<Integer>ans=new ArrayList<>();while(que.size()>0){int size=que.size();while(size-->0){TreeNode top=que.poll();if(top.right!=null){que.add(top.right);}if(top.left!=null){que.add(top.left);}

if(size==0){ans.add(top.val);}}}return ans;}

// RIGHT VIEW
public List<Integer>rightSideView(TreeNode root){if(root==null)return new ArrayList<>();Queue<TreeNode>que=new LinkedList<>();que.add(root);List<Integer>ans=new ArrayList<>();while(que.size()>0){int size=que.size();while(size-->0){TreeNode top=que.poll();if(top.left!=null){que.add(top.left);}if(top.right!=null){que.add(top.right);}if(size==0){ans.add(top.val);}}}return ans;}

//LEFT VIEW RECURSIVE
int mlevel=-1;//max level
public List<Integer>leftSideView(TreeNode root){mlevel=-1;List<Integer>ans=new ArrayList<>();leftSideView_(root,ans,0);return ans;}public void leftSideView_(TreeNode root,List<Integer>ans,int clevel){if(root==null){return;}if(clevel>mlevel){ans.add(root.val);mlevel=clevel;}leftSideView_(root.left,ans,clevel+1);leftSideView_(root.right,ans,clevel+1);}

int mlevel=-1;//max level
public List<Integer>rightSideView(TreeNode root){mlevel=-1;List<Integer>ans=new ArrayList<>();rightSideView_(root,ans,0);return ans;}public void rightSideView_(TreeNode root,List<Integer>ans,int clevel){if(root==null){return;}if(clevel>mlevel){ans.add(root.val);mlevel=clevel;}rightSideView_(root.right,ans,clevel+1);rightSideView_(root.left,ans,clevel+1);}

public class pair {
    TreeNode node = null;
    int state = 0;

    public pair(TreeNode node, int state) {
        this.node = node;
        this.state = state;
    }

}

    public List<Integer> topView(TreeNode root) {

        Queue<pair> que = new LinkedList<>();
        que.add(new pair(root, 0));
        int min = 1;
        int max = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (que.size() > 0) {
            int size = que.size();
            while (size-- > 0) {
                pair top = que.poll();// state-> width
                if (top.state < min || top.state > max) {
                    map.put(top.state, top.node.val);
                }
                min = Math.min(min, top.state);
                max = Math.max(max, top.state);
                if (top.node.left != null) {
                    que.add(new pair(top.node.left, top.state - 1));
                }
                if (top.node.right != null) {
                    que.add(new pair(top.node.right, top.state + 1));
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            ans.add(map.get(i));
        }
        return ans;
    }

public List<Integer> bottomView(TreeNode root) {
    
    Queue<pair> que=new LinkedList<>();
    que.add(new pair(root,0));
    int min=1;
    int max=-1;
    HashMap<Integer,Integer> map=new HashMap<>();
    while(que.size()>0){
        int size=que.size();
        while(size-->0){
            pair top=que.poll();//state-> width
                map.put(top.state,top.node.val);
            min=Math.min(min,top.state);
            max=Math.max(max,top.state);
            if(top.node.left!=null){
                que.add(new pair(top.node.left,top.state-1));
            }
            if(top.node.right!=null){
                que.add(new pair(top.node.right,top.state+1));
            }
        }
    }
    List<Integer> ans=new ArrayList<>();
        for(int i=min;i<=max;i++){
            ans.add(map.get(i));
        }
        return ans;
}