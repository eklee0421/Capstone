import json
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import permissions
from rest_framework.parsers import JSONParser
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from datetime import datetime
import time
#from bson import json_util, ObjectId

@api_view(['GET'])
def ping(request):
    if request.method == 'GET':
        return JsonResponse({
            "message" : "ok"
        })

@api_view(['POST'])
def geography(request):
    if request.method == 'POST':
        jsonVal = request.data
        latitude = jsonVal['latitude']
        logitude = jsonVal['longitude']
        return JsonResponse({
            "latitude" : latitude,
            "logitude" : logitude
        })