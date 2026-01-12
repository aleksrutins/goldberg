package main

import (
	"context"
	"os/exec"

	"farthergate.com/goldberg/config"
	"github.com/charmbracelet/log"
)

func main() {
	cfg, err := config.LoadFromPath(context.Background(), "goldberg.pkl")

	if err != nil {
		panic(err)
	}

	for _, step := range cfg.Steps {
		log.Print("STEP", "name", step.Name)
		for _, command := range step.Commands {
			log.Print("CMND", "cmd", command)
			cmd := exec.Command(command[0], command[1:]...)
			cmd.Stdout = nil
			cmd.Stderr = nil
			cmd.Stdin = nil
			err := cmd.Run()
			if err != nil {
				log.Error(err)
			}
		}
	}
}
