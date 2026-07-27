import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

class Solution {

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {

        var one = 0;
        var n = s.length();
        var ch = s.toCharArray();
        var oneIndex = new TreeMap<Integer, Integer>();
        var prefix = new int[n + 1];
        var pre = ch[0];
        var cnt = 0;
        var start = 0;
        var zeroIndex = new ArrayList<int[]>();
        for (var i = 0; i < n; i++) {
            if (ch[i] == pre) {
                cnt++;
            } else {
                if (pre == '1') {
                    oneIndex.put(start, cnt);
                    one += cnt;

                } else {
                    zeroIndex.add(new int[] { start, cnt });
                }
                start = i;
                pre = ch[i];
                cnt = 1;
            }

            prefix[i + 1] = prefix[i] + (ch[i] - '0');
        }

        if (pre == '0') {
            zeroIndex.add(new int[] { start, cnt });

        } else {
            oneIndex.put(start, cnt);
            one += cnt;
        }

        var result = new ArrayList<Integer>();
        if (zeroIndex.isEmpty() || zeroIndex.size() == 1) {
            for (int i = 0; i < queries.length; i++) {
                result.add(one);
            }

            return result;
        }

        var tree = new SegmentTree(n);

        for (var i = 0; i < zeroIndex.size() - 1; i++) {
            var z1 = zeroIndex.get(i);
            var z2 = zeroIndex.get(i + 1);
            tree.insert(1, z1[0], z1[1] + z2[1], 0, n - 1);
        }

        for (var q : queries) {
            var l = q[0];
            var r = q[1];

            if (prefix[r + 1] - prefix[l] == (r - l + 1)) {
                result.add(one);
                continue;
            }
            var ll = oneIndex.ceilingKey(l);
            var rr = oneIndex.floorKey(r);

            if (ll == null || rr == null || rr < l || ll > r) {
                result.add(one);
                continue;
            }
            var ans = one;
            if(ll.equals(rr)){
                if(ch[l] == '0' && ch[r] == '0'){
                    ans = Math.max(ans, one + (r - l + 1) - oneIndex.get(ll));
                    result.add(ans);
                    continue;
                }
                
                if(ch[r] == '1'){
                    result.add(ans);
                    continue;
                }
                if(ll == l){
                    result.add(ans);
                    continue;
                }
                
                 start= 0;
                var end = zeroIndex.size() - 1;
                while(start < end){
                    var mid = (start + end) >>1;
                    if(zeroIndex.get(mid)[0] >= l){
                        end=  mid;
                        
                    }else{
                        start = mid + 1;
                    }
                }
                var len = zeroIndex.get(end)[1] + (r - ll + 1);
                len -= oneIndex.get(ll);
                ans = Math.max(ans, one + len);
                result.add(ans);
                continue;
            }
            
            if(ch[r] == '0'){
                var r1 = oneIndex.lowerKey(rr);
                var len = r - r1 + 1;
                len -= (oneIndex.get(rr) + oneIndex.get(r1));
                ans = Math.max(ans, one + len);
            }
            
            if(ch[l] == '0'){
                var l1 = oneIndex.higherKey(ll);
                var len = l1 - l;
                len -= (oneIndex.get(ll));
                ans = Math.max(ans, one + len);
            }else if(ll != l){
                start= 0;
                var end = zeroIndex.size() - 1;
                while(start < end){
                    var mid = (start + end) >>1;
                    if(zeroIndex.get(mid)[0] >= l){
                        end=  mid;

                    }else{
                        start = mid + 1;
                    }
                }
                
                var l1 = oneIndex.higherKey(ll);
                var len = (l1 - ll ) + zeroIndex.get(end)[1];
                len -= oneIndex.get(ll);
                ans =Math.max(ans, one + len);
                
                
            }
            
            var r1 = oneIndex.lowerKey(rr);
            ans = Math.max(ans, one + tree.query(1, ll, r1, 0, n-1));
            
            
            result.add(ans);

        }

        return result;

    }
}

class SegmentTree {

    int tree[];

    public SegmentTree(int n) {

        tree = new int[4 * n];
    }

    public void insert(int ind, int i, int val, int l, int r) {

        if (i < l || r < i) {
            return;
        }

        if (l == r) {
            tree[ind] = val;
            return;
        }

        int mid = (l + r) >> 1;
        insert(2 * ind, i, val, l, mid);
        insert(2 * ind + 1, i, val, mid + 1, r);
        tree[ind] = Math.max(tree[2 * ind], tree[2 * ind + 1]);
    }

    public int query(int ind, int ql, int qr, int l, int r) {

        if (qr < l || r < ql) {
            return 0;
        }
        if (ql <= l && r <= qr) {
            return tree[ind];
        }

        int mid = (l + r) >> 1;

        return Math.max(query(2 * ind, ql, qr, l, mid), query(2 * ind + 1, ql, qr, mid + 1, r));
    }
}