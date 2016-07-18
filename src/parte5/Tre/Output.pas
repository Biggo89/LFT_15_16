integer m, n, tmp;
begin
   m := 123456;
   n := 30;
   while m <> 0 do begin
      if m < n then begin
	 tmp := m;
	 m := n;
	 n := tmp
      end;
      m := m - n
   end;
   print(n)
end
$