package config

type Step map[string]any

type Config struct {
	Name    string   `kdl:"name"`
	Version *string  `kdl:"version"`
	Use     []string `kdl:"use,multiple"`

	Source *struct {
		Repo string `kdl:"repo"`
	} `kdl:"source"`

	Steps map[string]Step `kdl:"step,multiple"`
}
