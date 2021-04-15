#!/usr/bin/env bash
adb shell am startservice -n behring.android.yaochibao/behring.android.yaochibao.android.service.PushReceivingService -e message "test message"