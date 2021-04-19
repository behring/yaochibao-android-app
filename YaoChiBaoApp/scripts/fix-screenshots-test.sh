#!/usr/bin/env bash
# https://github.com/facebook/screenshot-tests-for-android/issues/224
echo "Fix screenshots test in android 9+"
adb shell settings put global hidden_api_policy_p_apps 1
adb shell settings put global hidden_api_policy_pre_p_apps 1
adb shell settings put global hidden_api_policy 1