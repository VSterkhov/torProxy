��������� Expert Bundle* 
https://dist.torproject.org/torbrowser/8.0.8/tor-win64-0.3.5.8.zip
��������� � ������������ ����� � ������������� ��������� ����� � �����, ��� �����, ��� �� ������������ tor, �������� C:\tor.

� �������� C:\tor\Data\Tor ������� ���� �torrc� �� ��������� ����������.

torrc
DataDirectory c:\tor\Data\Tor
GeoIPFile c:\tor\Data\Tor\geoip
GeoIPv6File c:\tor\Data\Tor\geoip6

#��������� ������ �������� � ������
AvoidDiskWrites 1

#��������� ��������� � ���� �� ������� ����� ������� ������� SOCKS ������
#����� ������� ��������� ��������� ��� ������ ����������� � ������
SocksPort 192.168.10.10:9150
������� ����������� ������� � �����������.

cmd> netsh advfirewall firewall add rule name="Tor Proxy" dir=in action=allow localport=9150 protocol=tcp
������ ����� ������ ������� ����� �� ��������� ������� �������:
c:\tor\Tor\tor.exe -f c:\tor\Data\Tor\torrc
� ��������� ����� ����, ��� ���� ���������� ������ ����� ������ � ��������� ������������.

��������� � �������� ������.
��������� ������.

cmd> c:\tor\Tor\tor.exe --service install --options -f c:\tor\Data\Tor\torrc
����� ����� �� ������ �������� ��� ����� ��� ����������� ������, � ��� �� ����� �� ��������� ������ � ����� �Data�.

cmd> icacls c:\tor /grant "LOCAL SERVICE":(OI)(CI)RX
cmd> icacls c:\tor\Data /grant "LOCAL SERVICE":(OI)(CI)M
��������� ������.

cmd> net start tor
��������� Tor'� � �������� SOCKS-������ ���������. ������ ����������� ������� ������� �� ������������� ������ � ��������� IP ������� �� ������� ��������� ������ ��������� � ������ 9150.

chrome.exe --proxy-server="socks5://localhost:9150" --host-resolver-rules="MAP * 0.0.0.0 , EXCLUDE localhost"



