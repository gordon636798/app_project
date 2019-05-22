import re
import requests
from bs4 import BeautifulSoup
import shutil
import codecs

url = 'https://zh.moegirl.org/zh-tw/BanG_Dream%EF%BC%81%E5%B0%91%E5%A5%B3%E4%B9%90%E5%9B%A2%E6%B4%BE%E5%AF%B9%EF%BC%81/%E5%8D%A1%E7%89%8C%E5%88%97%E8%A1%A8'

#while True :

res =requests.get(url , timeout = 130)
soup = BeautifulSoup(res.text,'html.parser')

next_page = soup.find_all('a' , class_ = 'next')
    
    
img = soup.find_all('img', width = '100')

tr = soup.find_all('tr')

f = codecs.open('list.txt','w+','utf-8')

names=[]
imgs=[]
attrs=[]
skills=[]
scores=[]
for i in range(2,109):
    td = tr[i].find_all('td')
    if len(td) == 6:
        
        name = td[0].text
        img = td[1].find_all('img')[1]['src']
        attr = td[3].text
        skill = td[4].text
        score = td[5].text

        names.append(name)
        attrs.append(attr)
        skills.append(skill)
        scores.append(score)
f.write('<string-array name="name">\r\n')    
for i in range(len(names)):
    f.write('<item>')
    #print(names[i][2:])
    f.write(names[i])
    f.write('</item>\r\n')
f.write('</string-array>\r\n')

f.write('<string-array name="attr">\r\n')  
for i in range(len(attrs)):
    f.write('<item>')
    #print(attrs[i][2:])
    f.write(attrs[i])
    f.write('</item>\r\n')
f.write('</string-array>\r\n')

f.write('<string-array name="skill">\r\n')  
for i in range(len(skills)):
    f.write('<item>')
    #print(skills[i][2:])
    f.write(skills[i])
    f.write('</item>\r\n')
f.write('</string-array>\r\n')

f.write('<string-array name="score">\r\n')  
for i in range(len(attrs)):
    f.write('<item>')
    #print(scores[i][2:])
    f.write(scores[i])
    f.write('</item>\r\n')
f.write('</string-array>\r\n')

f.close()
    
"""    
i = 0
j = 0
for x in img :
    y = x['src']
    if requests.get(y).status_code == 200 and i%2 !=0 :
        name = 'pp' + str(j) +'.jpg'
        res2 = requests.get(y,stream=True,timeout = 130)
        f = open(name, 'wb')
        shutil.copyfileobj(res2.raw , f)
        f.close()
        j += 1
    i += 1
"""    
    #if len(soup.find_all('a', class_ = 'next no')) != 0 :
     #   break
   # url = 'https://forum.gamer.com.tw/C.php'+ next_page[0]['href']




