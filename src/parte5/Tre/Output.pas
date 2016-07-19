integer n,r,i;

begin
	n:=4;
	i:=n;
	r:=1;
	while(i>1)do
		begin
			r:=r*i;
			i:=i-1
		end;
	print(r)
end
$