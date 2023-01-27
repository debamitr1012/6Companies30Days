class Solution {
public:
    int maxUniqueSplit(string s) {
        uint32_t maxi = 1;
        int32_t N = s.size();
        for(uint32_t fences = 0; fences < (1<<N-1); ++fences){
            if(__builtin_popcount(fences) < maxi)
                continue;    
            std::unordered_set<std::string> S;
            std::string curr{s[0]};
            bool unique = true;
            for(int32_t bit = 0; bit < N-1; ++bit){
                if(fences>>bit&1){
                    if(!S.insert(curr).second){
                        unique = false;
                        break;
                    }
                    curr.clear();
                }
                curr.push_back(s[bit+1]);
            }
            if(!S.insert(curr).second)
                continue;
            if(unique)
                maxi = std::max(maxi,(uint32_t)S.size());
        }
        return maxi;
    }};