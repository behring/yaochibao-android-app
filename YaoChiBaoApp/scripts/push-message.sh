#!/usr/bin/env bash
message='{"type":"FOOD_COMMEND","data":\"{"id":"10003","name":"特价鸡排饭","priceCent":2300}\"}'
if [ "$1" ]; then
  message=$1
fi
adb shell am startservice -n behring.android.yaochibao/behring.android.yaochibao.android.service.PushReceivingService -e message "\"$message\""