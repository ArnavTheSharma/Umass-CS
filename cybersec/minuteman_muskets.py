import requests
#python3 ./umass/cybersec/minuteman_muskets.py

url = 'https://minuteman-muskets-xr6eisr4xa-uc.a.run.app/'

for i in range(0, 999):
    x = requests.post('https://minuteman-muskets-xr6eisr4xa-uc.a.run.app/guess', json = {"code": i})
    if "incorrect" not in x.text:
        print(i)
        print(x.text)
    if i%50 == 0:
        print(i)
        break
