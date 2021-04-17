#!/usr/bin/env bash
message='{"type":"FOOD_COMMEND","data":\"{"restaurantId":12313,"foodId":10003,"description":"特价鸡排饭"}\"}'
if [ "$1" ]; then
  message=$1
fi
adb shell am startservice -n behring.android.yaochibao/behring.android.yaochibao.android.service.PushReceivingService -e message "\"$message\""