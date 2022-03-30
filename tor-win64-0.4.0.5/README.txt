Скачиваем Expert Bundle* 
https://dist.torproject.org/torbrowser/8.0.8/tor-win64-0.3.5.8.zip
Скачиваем с официального сайта и распаковываем скачанный архив в папку, где хотим, что бы располагался tor, например C:\tor.

В каталоге C:\tor\Data\Tor создаем файл «torrc» со следующим содержимым.

torrc
DataDirectory c:\tor\Data\Tor
GeoIPFile c:\tor\Data\Tor\geoip
GeoIPv6File c:\tor\Data\Tor\geoip6

#Проводить меньше операций с диском
AvoidDiskWrites 1

#Локальный интерфейс и порт на котором будет слушать запросы SOCKS прокси
#Можно указать несколько вхождений для разных интерфейсов и портов
SocksPort 192.168.10.10:9150
Создаем разрешающее правило в брандмауэре.

cmd> netsh advfirewall firewall add rule name="Tor Proxy" dir=in action=allow localport=9150 protocol=tcp
Дальше можно просто создать ярлык со следующей строкой запуска:
c:\tor\Tor\tor.exe -f c:\tor\Data\Tor\torrc
И запускать через него, при этом консольное окошко будет висеть в окружении пользователя.

Установка в качестве службы.
Установка службы.

cmd> c:\tor\Tor\tor.exe --service install --options -f c:\tor\Data\Tor\torrc
Дадим права на чтение каталога где лежим сам исполняемый файлик, а так же права на изменения файлов в папке «Data».

cmd> icacls c:\tor /grant "LOCAL SERVICE":(OI)(CI)RX
cmd> icacls c:\tor\Data /grant "LOCAL SERVICE":(OI)(CI)M
Запускаем службу.

cmd> net start tor
Настройка Tor'а в качестве SOCKS-прокси закончена. Теперь настраиваем любимый браузер на использование прокси с указанием IP сервера на котором проводили данную настройку и портом 9150.

chrome.exe --proxy-server="socks5://localhost:9150" --host-resolver-rules="MAP * 0.0.0.0 , EXCLUDE localhost"



