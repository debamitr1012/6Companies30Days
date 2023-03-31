class Solution {
public:
    bool isPossible(vector<int>& n) 
{
	int out{};
	for(int C{1001}, m[2002][3]{}; const auto & n : n)
		if(m[C+n-1][0])                                             // 1
			--m[C+n-1][0], ++m[C+n][1];                
		else if(m[C+n-1][1])                                        // 2
			--m[C+n-1][1], ++m[C+n][2], --out;
		else if(m[C+n-1][2])                                        // 3
			--m[C+n-1][2], ++m[C+n][2];
		else ++m[C+n][0], ++out;                                    // 4
	return !out;
}
};