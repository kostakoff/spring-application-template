{{/*
define global labels helm chart
*/}}
{{- define "chart.labels" -}}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
calculated variables
*/}}

{{- define "app.fullName" -}}
{{ printf "%s-%s" .Values.projectName .Values.applicationName }}
{{- end -}}

{{- define "app.image" -}}
{{ .Values.images.app.registry }}/{{ .Values.images.app.repository }}/{{ .Values.images.app.imageName }}
{{- end -}}

{{- define "nginxproxy.image" -}}
{{ .Values.images.nginxproxy.registry }}/{{ .Values.images.nginxproxy.repository }}/{{ .Values.images.nginxproxy.imageName }}
{{- end -}}

{{- define "tests.image" -}}
{{ .Values.images.tests.registry }}/{{ .Values.images.tests.repository }}/{{ .Values.images.tests.imageName }}
{{- end -}}

{{/*
Create chart name and version as used by the chart label
*/}}
{{- define "app.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | lower }}
{{- end -}}

{{/*
custom
*/}}
