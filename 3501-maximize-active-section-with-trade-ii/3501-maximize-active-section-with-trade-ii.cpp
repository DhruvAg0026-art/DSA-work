struct segmentTree {
vector<int> tree;
    
public:
    segmentTree(int n) : tree(vector<int>(n << 2)) {}
    
    int query(int ind, int left, int right, int x, int y) {
        if (left >= x && right <= y) {
            return tree[ind];
        }
        const int mid = (left + right) >> 1;
        int r = 0;
        if (x <= mid) {
            r = query(ind << 1, left, mid, x, y);
        }
        if (y > mid) {
            r = max(r, query((ind << 1) | 1, mid + 1, right, x, y));
        } 
        return r;
    }

    void update(int ind, int left, int right, int x, int m) {
        tree[ind] = max(tree[ind], m);
        if (left >= x && right <= x) return;
        const int mid = (left + right) >> 1;
        if (x <= mid) {
            update(ind << 1, left, mid, x, m);
        } else {
            update((ind << 1) | 1, mid + 1, right, x, m);
        } 
    }
};



class Solution {
    int count(const string &s, int i) {
        const int n = s.length();
        int r = 0;
        for (const int j = i; i < n && s[i] == s[j]; ++i, ++r)
        ;
        return r;
    }

public:
    vector<int> maxActiveSectionsAfterTrade(string s, vector<vector<int>>& queries) {
        const int n = s.length();
        vector<pair<int, int>> v;
        for (int i = 0; i < n;) {
            const int c = count(s, i);
            v.push_back({i, c});
            i += c;
        }
        const int m = v.size();
        vector<int> id(n);
        segmentTree t(m);
        int one = 0;
        for (int i = 0, ind = 0; i < m; ++i) {
            if (s[ind] == '1') {
                one += v[i].second;
                if (i && i + 1 < m) {
                    t.update(1, 0, m - 1, i, v[i - 1].second + v[i + 1].second);
                }
            }
            for (int j = 0; j < v[i].second; ++j, id[ind++] = i)
            ;
        }
        vector<int> r;
        for (const auto & q : queries) {
            const int x = id[q[0]], y = id[q[1]], x1 = x + ('1' - s[q[0]]), y1 = y - ('1' - s[q[1]]);
            int temp = x1 + 2 <= y1 - 2 ? t.query(1, 0, m - 1, x1 + 2, y1 - 2) : 0;           
            if (s[q[0]] == '0' && y > x1) {
                temp = max(temp, (v[x1].first - q[0]) + (y == x1 + 1 ? (q[1] - v[y].first + 1) : v[x1 + 1].second));
            }
            if (s[q[1]] == '0' && x < y1) {
                temp = max(temp, (q[1] - v[y].first + 1) + (x == y1 - 1 ? (v[x].first - q[0] + 1) : v[y1 - 1].second));
            }
            r.push_back(temp + one);
        }
        return r;
    }
};